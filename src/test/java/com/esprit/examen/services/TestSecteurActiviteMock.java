package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
 class TestSecteurActiviteMock {


	@Mock //declaration d'un mock
	SecteurActiviteRepository sar;
	@InjectMocks
	SecteurActiviteServiceImpl sasi;
	
	SecteurActivite secta = SecteurActivite.builder().build();
	List<SecteurActivite> list = new ArrayList<SecteurActivite>() {
		{
			add(SecteurActivite.builder().build());
			add(SecteurActivite.builder().build());
		}
	};
	
	@DisplayName("Retrieving all activity sectors")
	@Test
	 void GetActSectTest() {
	List<SecteurActivite> lst = new ArrayList<>();		
		lst.add(new SecteurActivite());
		
		Mockito.when(sar.findAll()).thenReturn(lst);
		List<SecteurActivite> exp = sasi.retrieveAllSecteurActivite();
		
		assertEquals(exp, lst);
	
	
	}
	
	@DisplayName("Adding activity sectors...!")
	@Test
	 void AddActSectTest() {
		
		SecteurActivite secteur = new SecteurActivite(null, "filler code", "oussemaworks",null);
		
		Mockito.when(sar.save(secteur)).thenReturn(secteur);
		assertEquals(secteur, sasi.addSecteurActivite(secteur));
		
	}
	
	
	@DisplayName("Deleting activity sector...!")
	@Test
	  void DeleteTest() {
		SecteurActivite sa = new SecteurActivite();
		sa.setLibelleSecteurActivite("libelle");
		sa.setIdSecteurActivite((long) 3);
		Long said = sa.getIdSecteurActivite();
		Mockito.lenient().when(sar.findById(said)).thenReturn(Optional.of(sa));
		
		sasi.deleteSecteurActivite(said);
		verify(sar).deleteById(said);
	}
	
	@DisplayName("updating activity sector...!")
	@Test
	 void EditSecteurActivite_ifFound() {
		SecteurActivite sa_edit = new SecteurActivite();
		sa_edit.setIdSecteurActivite((long) 3);
		
		SecteurActivite new_sa_edit = new SecteurActivite();

		Mockito.lenient().when(sar.findById(sa_edit.getIdSecteurActivite())).thenReturn(Optional.of(sa_edit));
		sa_edit = sasi.updateSecteurActivite(new_sa_edit);
		
		verify(sar).save(sa_edit);
	}
	
	

	@DisplayName("retrieving activity sector by ID...!")
	@Test
	void retrieveSecteurActitiviteById() {

		Mockito.when(sar.findById(Mockito.anyLong())).thenReturn(Optional.of(secta));
		SecteurActivite secteuractivite = sasi.retrieveSecteurActivite((long) 8);
		assertNotNull(secteuractivite);

		verify(sar).findById(Mockito.anyLong());
	}

	
}
