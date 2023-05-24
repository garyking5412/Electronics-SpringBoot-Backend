package com.bkap.Services;

import com.bkap.DTOs.BillDTO;
import com.bkap.Model.Bill;
import com.bkap.Repositories.BillRepository;
import com.bkap.Utils.BillMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BillServiceImpl implements BillService{

    @Autowired
    private BillRepository billRepository;

    private final Logger logger  = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BillMapper billMapper;


    @Override
    public List<BillDTO> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        logger.info(">>>>>>>>Getting all bills>>>>>>>>>");
        return bills.stream().map(bill -> billMapper.convertBillToBillDto(bill)).collect(Collectors.toList());
    }

    @Override
    public BillDTO getBillByID(Integer billId) {
        Optional<Bill> bill = billRepository.findById(billId);
        logger.info(">>>>>>>>Getting bill info>>>>>>>>>>>>>");
        return billMapper.convertBillToBillDto(bill);
    }

    @Override
    @Transactional
    public BillDTO insertBill(BillDTO dto) {
        Bill bill = billMapper.convertBillDtoToBill(dto);
        Bill savedBill = billRepository.save(bill);
        logger.info(">>>>>>>>inserting bill info>>>>>>>>>>>>>");
        return billMapper.convertBillToBillDto(savedBill);
    }

    @Override
    @Transactional
    public BillDTO updateBill(BillDTO dto) {
        Bill bill = billMapper.convertBillDtoToBill(dto);
        Bill savedBill = billRepository.save(bill);
        logger.info(">>>>>>>>>>>updating bill info>>>>>>>>>>>>>>");
        return billMapper.convertBillToBillDto(savedBill);
    }

    @Override
    @Transactional
    public void deleteBill(Integer billId) {
        logger.info(">>>>>>>>>>>deleting bill info>>>>>>>>>>>>");
        billRepository.deleteById(billId);
    }
}