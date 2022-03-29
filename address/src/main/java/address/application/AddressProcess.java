package address.application;

import address.infrastructure.AddressApiCaller;
import address.utils.Console;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressProcess implements CommandLineRunner {

    //주소를 판단하는 주소 API https://www.juso.go.kr/addrlink/
    private final AddressApiCaller addressApiCaller;

    /**
     * 입력을 종료하고 싶으면 end를 입력하시오
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        //ex) input
        //성남, 분당 백 현 로 265, 푸른마을 아파트로 보내주세요!!
        //백현로
        System.out.println("주소를 입력하세요");
        String roughAddress = Console.readLine();
        String exceptAddress = exceptSpecial(roughAddress);
        System.out.println("exceptAddress = " + exceptAddress);

        String result = correctAddress(exceptAddress);
        System.out.println("result = " + result);


//        addressApiCaller.getAirQuality("강남대로");
    }

    /**
     * 정규식을 이용한 특수문자, 공백 제거
     * @param roughAddress
     * @return
     */
    public String exceptSpecial(String roughAddress){
        String result = "";
        for(int i = 0; i < roughAddress.length(); i++){
            if(String.valueOf(roughAddress.charAt(i)).matches("[a-zA-Z0-9 ㄱ-ㅎㅏ-ㅣ가-힣]")){
                result += roughAddress.charAt(i);
            }
        }
        return result.replaceAll(" ","");
    }

    public String correctAddress(String address){

        String convertedAddress = "";
        boolean breakFlag = false;
        //첫문자로 "로","길" 은 제외
        for(int i = 1; i < address.length(); i++){
            //글자수를 늘려가면서 판단
            if("로".equals(String.valueOf(address.charAt(i)))){
                String tempRoad = "";
                int strCnt = 0;
                for(int j = i; j >= 0; j--){
                    tempRoad = String.valueOf(address.charAt(j)) + tempRoad;

                    //도로명주소가 3글자 이상이면 판단 시작
                    if(strCnt >= 2){
                        //API 호출을 통해 도로명주소를 가져옴
                        System.out.println("tempRoad = " + tempRoad);
                        if(addressApiCaller.getAddressInfo(tempRoad).getResult().getCommonResponse().getTotalCount() > 0){

                            String getApiRoadName = addressApiCaller.getAddressInfo(tempRoad)
                                    .getResult()
                                    .getJusoResponses()
                                    .get(0)
                                    .getRoadName();

                            if(tempRoad.equals(getApiRoadName)) {
                                //호출한 도로명주소와 API 도로명 주소가 같으면 결과값 리턴
                                convertedAddress = getApiRoadName;
                                breakFlag = true;
                                break;
                            }
                        }

                    }
                    strCnt += 1;
                }
            }

            if("길".equals(String.valueOf(address.charAt(i)))){

            }
            if(breakFlag) break;
        }
        return convertedAddress;
    }
}
