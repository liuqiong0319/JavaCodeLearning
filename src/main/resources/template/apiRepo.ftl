package ${packageName};

import com.vip.qa.autov.core.api.repository.ApiSetting;
import com.vip.qa.autov.core.api.repository.ApiRepository;
import com.vip.qa.autov.core.params.ParamMap;
import ${responserType};

@ApiRepository
public interface ${className} {

	 <#list infos as info> 
	
	<#if info.name??>
	@ApiSetting("${info.name}")
	</#if>
	public ${info.returnType} ${info.method}(ParamMap<String,Object> params);
	 </#list>  
	         
 }