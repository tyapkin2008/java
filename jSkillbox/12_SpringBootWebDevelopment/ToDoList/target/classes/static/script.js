$(document).ready(function(){
    const deleteItem = function(){
		if(confirm("Удалить?")){
            var todo = $(this);
            var todoId = todo.data('id');
            $.ajax({
                method: "DELETE",
                url: '/todos/',
                data: {
                        id : todoId
                },
                success: function(response)
                {
                    todo.parent().remove();
                },
                error: function(response)
                {
                    if(response.status == 404) {
                        alert('Запись не найдена!');
                    } else {
                        alert("Ошибка удаления");
                    }
                }
            });
            return false;

		}
	};

	const deleteAll = function(){
		if(confirm("Удалить всё?")){
           $.ajax({
                method: "DELETE",
                url: "/todos/",
                success: function(response)
                {
                    // Очистим блок с заметками
                   $("#container_item").html("");
                },
                error: function(response)
                {
                    alert("Ошибка удаления");
                }
            });
            return false;
        }
	};

	const saveTodo = function(){
	   var text =  $("#text_input").val();
	   $.ajax({
           method: "POST",
           url: '/todos/',
           data: {
                text : text
           },
           success: function(response)
           {
               var todoId = response;
               var todoHtml =   '<div class="list-group-item">' +
                                    '<a data-id="' + todoId + '">' + text + '</a>' +
                                    '<span class="delete_btn" title="удалить" data-id="' + todoId + '">×</span>' +
                                '</div>';
               $('#container_item')
                           .append(todoHtml);
               closeForm();

           },
            error: function(response)
            {
                alert("Ошибка добавления");
            }
       });
       return false;
    };

    const closeForm = function(){
        $("#text_input").val("");
        return true;
    }

    $(document).on('click', '.delete_btn', deleteItem);
	$(document).on('click', '#delete_all_todo_btn', deleteAll);
	$(document).on('click', '#save_todo_btn', saveTodo);
	$(document).on('click', '#close_form_btn', closeForm);
});