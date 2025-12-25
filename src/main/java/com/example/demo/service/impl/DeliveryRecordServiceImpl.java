@Service
public class DeliveryRecordServiceImpl {

    @Autowired DeliveryRecordRepository deliveryRecordRepository;
    @Autowired ContractRepository contractRepository;

    public DeliveryRecord createDeliveryRecord(DeliveryRecord r) {
        if (r.getDeliveryDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Delivery date cannot be in the future");

        contractRepository.findById(r.getContract().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        return deliveryRecordRepository.save(r);
    }

    public DeliveryRecord getLatestDeliveryRecord(Long id) {
        return deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id)
            .orElseThrow(() -> new ResourceNotFoundException("No delivery records found"));
    }

    public DeliveryRecord getRecordById(Long id) {
        return deliveryRecordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Delivery record not found"));
    }

    public List<DeliveryRecord> getDeliveryRecordsForContract(Long id) {
        return deliveryRecordRepository.findByContractIdOrderByDeliveryDateAsc(id);
    }
}
