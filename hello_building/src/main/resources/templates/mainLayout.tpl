html{
	head{
		title(' Create Building');
		link(rel:'stylesheet', href:'//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css')
		link(rel:'stylesheet', href:'//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css')
		
	}
	body
	{
		
		div(class:'container') {
		comment('static nav bar')
		
			div(class:'navbar navbar-default',role:"navigation") {
			  div(class:'container-fluid') {
				  div( class:"navbar-header"){
					  a(class:"navbar-brand", href:"/",'Building Machine')
				  }
				div(class:'navbar-collapse collapse'){
					ul(class:'nav navbar-nav') {
						li {
						  a(href:'/') {
							yield 'Building Creator'
						  }
						}
						li {
							a(href:'/list') {
								yield 'Building List'
							}
						}
					  }
				}
			
			  }
			}
		h1(){
			small( 'Reggie Buildings')
		}
		br()
		div(class:"container") { content() }
		script(src:'//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js')
  }
  }
}