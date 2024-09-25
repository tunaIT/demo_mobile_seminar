//package matcha.banking.be.service;
//
//import lombok.RequiredArgsConstructor;
//import matcha.banking.be.dao.BillDao;
//import matcha.banking.be.dao.PaymentDao;
//import matcha.banking.be.dto.SearchPaymentDto;
//import matcha.banking.be.entity.BillEntity;
//import matcha.banking.be.entity.PaymentEntity;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class BillService {
//    private final BillDao billDao;
//    private final UserService userService;
//    private final PaymentService paymentService;
//
//    public BillEntity getBill(String code, String category) {
//        return billDao.findByCodeAndCategory(code, category).orElseThrow(() -> new EmptyResultDataAccessException("Bill not found", 1));
//    }
//
//    public void payBill(String code, String token) {
//
//        BillEntity billEntity = billDao.findByCode(code).orElseThrow(() -> new EmptyResultDataAccessException("Bill not found", 1));
//        userService.payBill(billEntity.getAmount() -( billEntity.getFee() + billEntity.getTax()), token);
//        paymentService.payBill(billEntity, token);
//        billDao.delete(billEntity);
//    }
//
//}
