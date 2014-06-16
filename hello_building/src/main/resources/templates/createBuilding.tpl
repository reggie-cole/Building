layout 'mainLayout.tpl',content: contents {
	div(class:"jumbotron"){
		form(role:"form",id:'messageForm',action:'/' , method:'post')
		{
		
			   div(class:"form-group"){
					label (for:'buildingAddress' , 'Building Address: ', class:"col-sm-2 control-label")
					input(name:'buildingAddress',type:'text', class:buildingAddress)
					
				}
				div(class:"form-group"){
					label (for:'buildingName', 'Building Name:', class:"col-sm-2 control-label")
					input (name:'buildingName', type:'text', class:buildingName )
					
				}
				div(class:"form-group"){
					label (for:'length', 'Building Length:', class:"col-sm-2 control-label")
					input (name:'length', type:'number',class:length )
					
				}
				div(class:"form-group"){
					label (for:'width', 'Building Width:', class:"col-sm-2 control-label")
					input (name:'width', type:'number',class:width )
				}
				div (class:'form-actions'){
					
					button(class:"btn btn-info",type:"submit", "Submit")
				}
			
			
		}
	}
   
}