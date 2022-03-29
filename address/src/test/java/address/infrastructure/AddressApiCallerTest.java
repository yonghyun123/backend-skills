package address.infrastructure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressApiCallerTest {
    @Autowired
    AddressApiCaller addressApiCaller;

    @Test
    public void 주소_API통신(){
        Object roads = addressApiCaller.getAirQuality("강남대로");
        Assertions.assertThat(roads).isNotNull();
    }

}