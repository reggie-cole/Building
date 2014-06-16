layout 'mainLayout.tpl',content: contents {
    form(id:'messageForm',action:'/' , method:'post')
	{

		label (for:'buildingAddress' , 'building Address:')
		input(name:'buildingAddress',type:'text', class:buildingAddress)
		br()
		label (for:'buildingName', 'Building Name:')
		input (name:'buildingName', type:'text', class:buildingName ) 
           
		br()
		label (for:'length', 'Building Length:')
		input (name:'length', type:'number',class:length ) 
		br()
		label (for:'width', 'Building Width:')
		input (name:'width', type:'number',class:width ) 
		br()
		div (class:'form-actions'){
			input(type:'submit',value:'enter')
		}	
    }
}