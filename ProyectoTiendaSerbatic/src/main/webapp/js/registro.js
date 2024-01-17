var provincias_1=new Array("-","Andalucía","Asturias","Baleares","Canarias","Castilla y León","Castilla-La Mancha","...") 
var provincias_2=new Array("-","Salta","San Juan","San Luis","La Rioja","La Pampa","...") 
var provincias_3=new Array("-","Cali","Santamarta","Medellin","Cartagena","...") 
var provincias_4=new Array("-","Aisne","Creuse","Dordogne","Essonne","Gironde ","...")

var todasProvincias = [
   [],
   provincias_1,
   provincias_2,
   provincias_3, 
];

function cambia_provincia(){ 
    //tomo el valor del select del pais elegido 
    var pais 
    pais = document.f1.pais[document.f1.pais.selectedIndex].value 
    //miro a ver si el pais está definido 
    if (pais != 0) { 
       //si estaba definido, entonces coloco las opciones de la provincia correspondiente. 
       //selecciono el array de provincia adecuado 
       mis_provincias=todasProvincias[pais] 
       //calculo el numero de provincias 
       num_provincias = mis_provincias.length 
       //marco el número de provincias en el select 
       document.f1.provincia.length = num_provincias 
       //para cada provincia del array, la introduzco en el select 
       for(i=0;i<num_provincias;i++){ 
          document.f1.provincia.options[i].value=mis_provincias[i] 
          document.f1.provincia.options[i].text=mis_provincias[i] 
       }	
    }else{ 
       //si no había provincia seleccionada, elimino las provincias del select 
       document.f1.provincia.length = 1 
       //coloco un guión en la única opción que he dejado 
       document.f1.provincia.options[0].value = "-" 
       document.f1.provincia.options[0].text = "-" 
    } 
    //marco como seleccionada la opción primera de provincia 
    document.f1.provincia.options[0].selected = true 
}