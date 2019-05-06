package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    private Object GeoIPService;

    @Test
    public void testMyIp(){
        GeoIPService  = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("95.158.49.234");
        Assert.assertEquals(GeoIPService,"<GeoIP><Country>UA</Country><State>12</State></GeoIP>");
    }
    @Test
    public void testInvalidIp(){
        GeoIPService  = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("95.158.49.xxx");
        Assert.assertEquals(GeoIPService,"<GeoIP><Country>UA</Country><State>12</State></GeoIP>");
    }
}
