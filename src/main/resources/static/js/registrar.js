// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	//on ready
	
});

async function registrarUsuarios(){
	//crear la variable que mande en una lista todo lo que queremos en este caso los datos del usuario
	let datos={};
	
	//optenemos los valores 
	datos.nombre= document.getElementById('txtNombre').value;
	datos.apellido=document.getElementById('txtApellido').value;
	datos.email= document.getElementById('txtEmail').value;
	datos.telefono= document.getElementById('txtTelefono').value;
	datos.password=document.getElementById('txtPassword').value;
	
	let confirmarPassword= document.getElementById('txtRepetirPassword').value
	
	//condicional para validar que la password es igual
	
	if(confirmarPassword != datos.password){
		alert('La contrasenia no es igual')
		return;
	}
	
	//hace referencia a la clase de java y de ahi va a sacar los nombrs en una lista 
  const request = await fetch('api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  alert('Registro exitoso');
  window.location.href='login.html';


  //console.log(usuarios);
  
 
}

