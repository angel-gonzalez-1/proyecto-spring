// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	//on ready
	
});

async function iniciarSesion(){
	//crear la variable que mande en una lista todo lo que queremos en este caso los datos del usuario
	let datos={};
	
	//optenemos los valores de nuestra vista 
	
	datos.email= document.getElementById('txtEmail').value;
	datos.password=document.getElementById('txtPassword').value;
	
	
	//hace referencia a la clase de java y de ahi va a sacar los nombrs en una lista 
  const request = await fetch('api/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  const respuesta = await request.text();
  
  //si la respuesta es ok manda a nuestra pagina de usuarios
  if(respuesta != 'FAIL'){
	
	//GUARDAMOS EL TOKEN EN EL LADO DEL USUSARIO
	localStorage.token=respuesta;
	localStorage.email=datos.email;
	window.location.href='usuarios.html'
	}else{
		alert('Las credenciales son incorrectas. intente nuevamente');
	}


  //console.log(usuarios);
  
 
}
