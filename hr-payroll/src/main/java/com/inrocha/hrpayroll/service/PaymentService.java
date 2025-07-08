package com.inrocha.hrpayroll.service;

import com.inrocha.hrpayroll.feign.client.WorkerFeignClient;
import com.inrocha.hrpayroll.model.Payment;
import com.inrocha.hrpayroll.model.Worker;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }

    public Payment getPayment(long workerId, int days) {
        Worker worker = workerFeignClient.findById(workerId).getBody();
        if (worker == null) {
            return null;
        }
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}