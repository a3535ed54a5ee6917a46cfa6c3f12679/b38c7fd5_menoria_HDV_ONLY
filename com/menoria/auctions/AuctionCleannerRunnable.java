package com.menoria.auctions;

import java.util.Iterator;

public class AuctionCleannerRunnable implements Runnable{

	@Override
	public void run() {
		Iterator<AuctionItem> iter = AuctionEngine.getInstance().getAuctionItems().iterator();
		while (iter.hasNext()) {
			AuctionItem item = iter.next();
			if (item.hasExpired()) {
				
				if (item.isOwner()) {
					// TODO: send message
				}
				
				iter.remove();
			}
		}
	}

}
