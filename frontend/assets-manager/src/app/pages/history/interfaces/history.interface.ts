export interface TransactionDetail {
  id: number;
  transactionId: number;
  assetId: number;
  assetSerialNumber: string;
  assetModel: string;
  direction: string;
  reason: string;
}

export interface Transaction {
  id: number;
  transactionType: string;
  transactionDate: string;
  employeeId: number;
  employeeName: string;
  note: string;
  details: TransactionDetail[];
}
