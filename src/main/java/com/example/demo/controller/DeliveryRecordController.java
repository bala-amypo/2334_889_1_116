@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    @Autowired
    private DeliveryRecordService deliveryRecordService;

    @PostMapping
    public DeliveryRecord create(@RequestBody DeliveryRecord record) {
        return deliveryRecordService.createDeliveryRecord(record);
    }

    @GetMapping("/{id}")
    public DeliveryRecord get(@PathVariable Long id) {
        return deliveryRecordService.getRecordById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<DeliveryRecord> list(@PathVariable Long contractId) {
        return deliveryRecordService.getDeliveryRecordsForContract(contractId);
    }
}
