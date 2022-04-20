document.addEventListener('DOMContentLoaded', function(){
    document.getElementById('load_button').onclick = function (event){
        alert("It works")
        event.preventDefault()
        ajaxSubmitForm()
    }
})

function ajaxSubmitForm(){
    let form = document.getElementById('client_form')
    let formData = new FormData(form)
    let json = JSON.stringify(Object.fromEntries(formData))
    let request = new XMLHttpRequest()
    request.open('post', '/rest/client-form')
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8')
    request.send(json)
    request.addEventListener('readystatechange', function (){
        if(request.readyState!==4) return
        if(request.status==200){
            let clients = JSON.parse(request.responseText)
            clients={
                'clients': clients
            }
            const html=`
                <table class="table">
                {{#clients}}
                <tr>
                    <td>{{id}}</td>
                    <td>{{surname}}</td>
                    <td>{{name}}</td>
                    <td>{{patronymic}}</td>
                    <td>{{email}}</td>
                    <td>{{birthDate}}</td>
                    <td>{{gender}}</td>
                </tr>
                {{/clients}}
                </table>
            `
            let result = mustache.render(html, clients)
            let output = document.getElementById('output')
            output.innerHTML=result
        } else alert(request.statusText)
    })
}