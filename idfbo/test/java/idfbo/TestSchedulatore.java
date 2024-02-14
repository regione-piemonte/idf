/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package idfbo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import it.csi.idf.idfbo.scheduling.SpringSchedulingConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringSchedulingConfig.class }, loader = AnnotationConfigContextLoader.class)
public class TestSchedulatore {

	@Test
	public void testSchedulazione() throws InterruptedException {
		//Thread.sleep(500000);
	}

}
