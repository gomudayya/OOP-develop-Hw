//2019136149 박건우
//VendingMachine State ( InventoryEmpty , InventoryNoEmpty ) 
public enum VMState {
	InventoryEmpty{
		//품절상태(인벤토리 비어있음 상태) 이면 insert , select는 빈 메소드  
		public void insertCash(VendingMachine VM, Currency currency, int amount) {}
		public void selectItem(VendingMachine VM, Item item) {}		
		public void cancel(VendingMachine VM) {
			VM.returnChange();
		}
	}, 
	InventoryNoEmpty{
		
		public void insertCash(VendingMachine VM, Currency currency, int amount) {
			VM.addCash(currency, amount);
		}
		
		public void selectItem(VendingMachine VM, Item item) throws ChangeNotAvailableException {
			// 아이템을 살 수 없을때 예외 던지기
			if(!VM.canBuyItem(item)) {
				if (VM.canBuyAnyItem()) throw new ChangeNotAvailableException(false);
				VM.cancel();
				throw new ChangeNotAvailableException(true);
			}
			
			// 아이템을 살 수 있으면 dispenseItem(), 이후 상태를 확인하고 cancel을 호출하거나 상태 전이
			dispenseItem(VM,item);
			if (!VM.canBuyAnyItem()) VM.cancel();
			if (VM.isEmpty()) {
				VM.changeToInventoryEmptyState();
				VM.cancel();
			}
		}
		
		public void cancel(VendingMachine VM) {
			VM.returnChange();
		}
	};
	public abstract void insertCash(VendingMachine VM, Currency currency, int amount);
	public abstract void selectItem(VendingMachine VM, Item item) throws ChangeNotAvailableException;
	public abstract void cancel(VendingMachine VM);
	
	public void dispenseItem(VendingMachine VM, Item item) throws ChangeNotAvailableException {
		CashRegister changeRegister = VM.getChange(VM.getInsertedBalance() - item.price);
		VM.setUserCashRegister(changeRegister);
		VM.removeItem(item);
	}
	
}
