// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	cargarUsuarios();
  $('#usuarios-Table').DataTable();
  actualizarEmailUsuario();
});

function actualizarEmailUsuario(){
	document.getElementById('txt-email-usuario').outerHTML= localStorage.email
}

async function cargarUsuarios(){
	//hace referencia a la clase de java y de ahi va a sacar los nombrs en una lista 
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers:getheader()
  });
  const usuarios = await request.json();

  

  console.log(usuarios);
  
 let listadodeUsuarios = '';
  for (let usuariollenar of usuarios){
	
	//vamos a obtener el usuario a traves del click a traves de que se llena
	 let botonEliminar='<a href="#" onclick="eliminarUsuario('+usuariollenar.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
  // esta condicional es un if corto donce pregunta que si es nulo retornara un - y si no el telefono
  	//let telefonoTexto =usuariollenar.telefono == null ? '-' : usuariollenar.telefono
	let cargarTabla = '<tr><td> '+usuariollenar.id+' </td> <td>' +usuariollenar.nombre
	+ ' '+usuariollenar.apellido+'</td> <td> '+usuariollenar.email+' </td> <td> '+botonEliminar
	+'</td></tr>';
  
  listadodeUsuarios+=cargarTabla;
}
  
  document.querySelector('#usuarios-Table tbody').outerHTML=listadodeUsuarios;

}

//metodo header
function getheader(){
	return {
		
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
	};
}

//metodo de ajax para eliminar un usurio
async function eliminarUsuario(id){
	//alert(id);
	
	if(!confirm('Desea Eliminar Este Usuario?')){
		return;
	}
	const request = await fetch('api/usuario/' + id, {
    method: 'DELETE',
    headers: getheader()
  });
  location.reload();
  
}
