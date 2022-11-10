package tn.esprit.rh.achat.services;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;


import java.util.Arrays;

import java.util.List;

import java.util.Optional;


import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;


import tn.esprit.rh.achat.entities.Stock;

import tn.esprit.rh.achat.repositories.StockRepository;

import tn.esprit.rh.achat.services.StockServiceImpl;





@SpringBootTest

 class StockServiceImplTest {


	@MockBean

	StockRepository repo;

	

	@Autowired

	StockServiceImpl srvce;

	

	

	@Test

	 void TestretrieveAllStocks()

	{

		Stock stock1 = new Stock("TestMock1",10,2);

		Stock stock2= new Stock("TestMock2",4,1);

		Stock stock3 = new Stock("TestMock3",100,85);

		List<Stock> stocks = Arrays.asList(stock1,stock2,stock3) ;

		

		//ngolo biha le repo traje3li data li f list stocks l 3malnha statique

		BDDMockito.given(repo.findAll()).willReturn(stocks);

		

		//test

		assertThat(srvce.retrieveAllStocks()).hasSize(3).contains(stock1,stock2,stock3);

		

	}

	@Test

	 void TestretrieveStock()

	{

		Stock stock1 = new Stock(1L,"TestMock1",10,2);

		BDDMockito.given(repo.findById(anyLong())).willReturn(Optional.ofNullable(stock1));

		Stock result = srvce.retrieveStock(1L);

		assertThat(result.getLibelleStock()).containsIgnoringCase("TestMock"); 	 	

	}

	

	@Test

	 void TestaddStock()

	{

		Stock stock = new Stock(1L,"addStock",100,50);

		when(repo.save(stock)).thenReturn(stock);

		assertEquals(stock, srvce.addStock(stock));

	}

	

	@Test

	 void TestupdateStock()

	{

		Stock stock = new Stock(1L,"addStock",100,50);

		when(repo.save(stock)).thenReturn(stock);

		stock.setLibelleStock("updatestok");

		stock.setQte(90);

		assertEquals(stock, srvce.updateStock(stock));

	}

	

	

	/*

	@Test

	 void TestdeleteStock()

	{

		Stock stock = new Stock(1L,"addStock",100,50);

		BDDMockito.given(repo.findById(anyLong())).willReturn(Optional.ofNullable(stock));

		srvce.deleteStock(stock.getIdStock());

		assertEquals(stock, srvce.deleteStock(stock.getIdStock()));

	}

	*/

	

	

	@Test

	void TestretrieveStatusStock ()

	{

		

	}

	


	

	

	

	

	

	


}

