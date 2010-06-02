package tests;

import gui.Memento;
import org.junit.Assert;
import org.junit.Test;

public class MementoTests {

    @Test
    public void identifyHeightTest() throws Exception {
        Assert.assertEquals(14, Memento.identifyHeight("height=14"));
    }

    @Test
    public void identifyWidthTest() throws Exception {
        Assert.assertEquals(144, Memento.identifyWidth("width=144"));
    }

    @Test
    public void identifyCoordinateXTest() throws Exception {
        Assert.assertEquals(23, Memento.identifyCoordinateX("coordinateX=23"));
    }

    @Test
    public void identifyCoordinateYTest() throws Exception {
        Assert.assertEquals(67, Memento.identifyCoordinateY("coordinateY=67"));
    }

    @Test
    public void getPositivNumberTest() throws Exception {
        Assert.assertEquals(899, Memento.getNumber("899"));
    }

    @Test
    public void getNegativeNumberTest() throws Exception {
        Assert.assertEquals(-899, Memento.getNumber("-899"));
    }
}
