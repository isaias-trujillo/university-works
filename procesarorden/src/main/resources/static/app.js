$(document).ready(function(){
    connect();
    const btnCerrar = document.querySelectorAll(".btn-close");
    btnCerrar.forEach(function (btn){
        btn.addEventListener('click', function (){
            btn.parentElement.remove();
        })
    });
});

function connect(){
    var socket = new SockJS('/websocket-connect');
    stompClient = Stomp.over(socket);
    stompClient.connect({},function (frame){
        stompClient.subscribe('/topic/in-memory', function (data){
            let estado = document.querySelector(".estado");
            const p = document.createElement("DIV");
            var respuesta = JSON.parse(data.body)
            if(respuesta['codigo']==='2'){
                p.classList.add(
                    "alert",
                    "alert-success",
                    "alert-dismissible",
                    "w-75",
                    "fade",
                    "show",
                );
                p.innerHTML =
                    `<strong>"${respuesta['mensaje']}"</strong> Espere la fecha de entrega
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`
                ;
            } else{
                p.classList.add(
                    "alert",
                    "alert-danger",
                    "alert-dismissible",
                    "w-75",
                    "fade",
                    "show"
                );
                p.innerHTML =
                    `<strong>"${respuesta['mensaje']}"</strong> Corrija para que la orden se procese
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`
                ;
            }
            //p.textContent = data.body;
            //p.classList.add("recibido")
            estado.appendChild(p);
        })
    })
}
