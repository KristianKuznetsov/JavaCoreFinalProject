package crs.jcfpapp.services;

import crs.jcfpapp.utils.log.LogCreator;
import crs.jcfpapp.utils.writer.fileWriter.ReportWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransferHandler {
    public static HashMap<String, Double> transfer(ArrayList<Record> operations, HashMap<String, Double> base) {
        ReportWriter rw = new ReportWriter();

        for (Record el : operations) {
            boolean f0 = true;
            boolean f1 = true;
            boolean f2 = true;

            if(el.getAmount() < 0){
                f0 = false;
            }

            if (base.containsKey(el.getInAccount())) {
                if(base.get(el.getInAccount()) < el.getAmount()){
                    f1 = false;
                }
            } else {
                f2 = false;
            }

            if(f0 && f1 && f2){
                if (base.containsKey(el.getOutAccount())) {
                    base.put(el.getOutAccount(), el.getAmount() + base.get(el.getOutAccount()));
                } else {
                    base.put(el.getOutAccount(), el.getAmount());
                }
                rw.writeTransLog(LogCreator.logSuccessfullyTransfer(el));
            } else {
                rw.writeTransLog(LogCreator.logProcessingError(el, new ArrayList<>(List.of(!f0, !f1, !f2))));
            }
        }

        return base;
    }
}

