//package matcha.banking.be.service;
//
//import lombok.RequiredArgsConstructor;
//import matcha.banking.be.dao.PaymentDao;
//import matcha.banking.be.dto.SearchPaymentDto;
//import matcha.banking.be.entity.BillEntity;
//import matcha.banking.be.entity.PaymentEntity;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PaymentService {
//    private final PaymentDao paymentDao;
//    private final UserService userService;
//
//    public List<PaymentEntity> searchPayment(SearchPaymentDto searchPaymentDto) {
//        List<PaymentEntity> paymentEntities = paymentDao.findAll();
//        if (searchPaymentDto.getCategory() != null) {
//            paymentEntities.removeIf(paymentEntity -> !paymentEntity.getCategory().equals(searchPaymentDto.getCategory()));
//        }
//        if (searchPaymentDto.getForUser() != null) {
//            paymentEntities.removeIf(paymentEntity -> !paymentEntity.getForUser().equals(searchPaymentDto.getForUser()));
//        }
//        return paymentEntities;
//    }
//
//    public List<PaymentEntity> searchMyPayment(String category, String token) {
//        String cardNumberfromToken = userService.getCardNumberfromToken(token);
//        List<PaymentEntity> paymentEntities = paymentDao.findAll();
//        paymentEntities.removeIf(paymentEntity -> !paymentEntity.getForUser().equals(cardNumberfromToken));
//        if (category != null) {
//            paymentEntities.removeIf(paymentEntity -> !paymentEntity.getCategory().equals(category));
//        }
//        return paymentEntities.stream().sorted(
//                (t1, t2) -> t2.getCreated().compareTo(t1.getCreated())
//        ).toList();
//    }
//
//    public void payBill(BillEntity billEntity, String token) {
//        PaymentEntity paymentEntity = new PaymentEntity();
//        paymentEntity.setForUser(userService.getCardNumberfromToken(token));
//        paymentEntity.setCategory(billEntity.getCategory());
//        paymentEntity.setAmount(billEntity.getAmount() - (billEntity.getFee() + billEntity.getTax()));
//        paymentEntity.setCreated(LocalDateTime.now());
//        paymentEntity.setStatus("Successfully");
//        paymentDao.save(paymentEntity);
//    }
//}
