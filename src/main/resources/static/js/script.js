// Variaveis DOM geral
const tituloNoticia = document.getElementsByName("tituloNoticia")[0];
const entradaImagem = document.getElementsByName("linkImagem")[0];
const viewImagem = document.getElementsByClassName("preview")[0];
const autor = document.getElementsByName("autor")[0];
const corpoNoticia = document.getElementsByName("corpoNoticia")[0];
const botaoEnviar = document.getElementsByName("btEnviar")[0];
const autorNome = document.getElementsByName("nome")[0];
const autorCpf = document.getElementsByName("cpf")[0];
const todosInputs = document.querySelectorAll(".form-control");

// Data
const inputData = document.getElementsByClassName("inputData")[0];

function atualizarHora() {
	var data = new Date();
	var dia = (data.getDate() < 10 ? '0' : '') + data.getDate();
	var mes = data.getMonth() + 1;
	var ano = data.getFullYear();
	var hora = data.getHours();
	var min = (data.getMinutes() < 10 ? '0' : '') + data.getMinutes();
	var sec = (data.getSeconds() < 10 ? '0' : '') + data.getSeconds();

	inputData.value = dia + '/0' + mes + '/' + ano + ' às ' + hora + ':'
			+ min + ':' + sec;

	setTimeout("atualizarHora()", 1000);
};

// Validações client-side


function validarImagem(src) {
	var img = new Image();
	img.src = src;
	img.onload = function(){
		if (img.height > 0) {
			viewImagem.src = src;
			viewImagem.style.display = "block";
			entradaImagem.style.border = "1px solid green"
				return true;
		}
	}
	entradaImagem.style.border = "1px solid red"
	viewImagem.style.display = "none";
};


function validarCampo(campo){
	const invalido = () =>{campo.classList.remove("valido");campo.classList.add("invalido")}
	const valido = () =>{campo.classList.remove("invalido");campo.classList.add("valido")}
	var valor = campo.value;
	switch(campo.name){
	case "tituloNoticia": if(valor.length<8){invalido()}else{valido();}break;;
	case "autor" : if(valor==""){invalido()}else{valido();} break;;
	case "corpoNoticia" : if(valor.length<50){invalido()}else{valido();} break;;
	case "nome" : if(valor.length<8){invalido()}else{valido();}break;;
	case "cpf" : if(valor.length<11){invalido()}else{valido();}break;;
	}
}


autorCpf.addEventListener("keyup", function(){
   if(autorCpf.value) autorCpf.value = autorCpf.value.replace(/[^\d]+/g,'');
});

autorNome.addEventListener("keyup", function(){
	   if(autorNome.value) autorNome.value = autorNome.value.replace(/[0-9]/g,'');
});
