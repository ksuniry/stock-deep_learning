package com.stock.mvc.vo.koreanInvestment;

import org.apache.commons.lang3.builder.ToStringBuilder;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Hoga20VO {
	
	String isuSrtCd;
	
	String id;
	
	String info;
	
	 @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
}
