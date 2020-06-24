package org.foot.server;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

public class ReservationTest extends DataSourceBasedDBTestCase {

    @Override
    protected DataSource getDataSource() {
        return null;
    }


    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder()
                .build(
                        getClass()
                        .getClassLoader()
                        .getResourceAsStream("data.xml")
                );
    }

    @Test
    public void searchTest(){

    }




}
