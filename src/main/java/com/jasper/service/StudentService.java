package com.jasper.service;

public interface StudentService {
   public byte[] getReport();
   public byte[] getDynamicReport();

   public byte[] getDynamicReportFromDBData();

   public byte[] getStaticReport();
}
