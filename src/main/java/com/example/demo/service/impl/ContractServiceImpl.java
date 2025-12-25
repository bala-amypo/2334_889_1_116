@Service
public class ContractServiceImpl {

    @Autowired ContractRepository contractRepository;
    @Autowired DeliveryRecordRepository deliveryRecordRepository;

    public Contract createContract(Contract c) {
        if (c.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Base contract value must be positive");
        return contractRepository.save(c);
    }

    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
    }

    public Contract updateContract(Long id, Contract u) {
        Contract c = getContractById(id);
        c.setTitle(u.getTitle());
        c.setCounterpartyName(u.getCounterpartyName());
        c.setAgreedDeliveryDate(u.getAgreedDeliveryDate());
        c.setBaseContractValue(u.getBaseContractValue());
        return contractRepository.save(c);
    }

    public void updateContractStatus(Long id) {
        Contract c = getContractById(id);
        deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id)
            .ifPresentOrElse(
                dr -> c.setStatus(
                    dr.getDeliveryDate().isAfter(c.getAgreedDeliveryDate()) ? "BREACHED" : "ACTIVE"),
                () -> c.setStatus("ACTIVE")
            );
        contractRepository.save(c);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
}
