const CLIENTS_TAB = `
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

$(document).ready(function (){
    $('#loadButton').click(function (event){
        event.preventDefault()
        ajaxSubmitForm()
    })
})

function ajaxSubmitForm(){
    let form=$('#clientForm')[0]
    let formData=new FormData(form)
    let json=JSON.stringify(Object.fromEntries(formData))
    $.ajax({
        type: 'post',
        url: '/rest/clientForm',
        data: json,
        processData: false,
        contentType: 'application/json; charset=utf-8',
        success:function(data, textStatus, jqXHR){
            let clients = JSON.parse(jqXHR.responseText)
            clients={
                'clients':clients
            }
            let result=mustache.render(CLIENTS_TAB, clients)
            $('#output').html(result)
        },
        error:function(jqXHR, textStatus, errorThrown){
            alert(textStatus)
        }
    })
}