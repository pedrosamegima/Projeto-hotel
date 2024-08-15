
$(document).ready(function() {
	loadGuests();

	$('#guestForm').submit(function(event) {
		event.preventDefault();
		const id = $('#guestId').val();
		if (id) {
			updateGuest(id);
		} else {
			addGuest();
		}
	});
});

function loadGuests() {
	$.getJSON('/quarto/', function(data) {
		$('#guestTableBody').empty();
		data.forEach(guest => {
			$('#guestTableBody').append(
				`<tr>
					<td>${guest.id}</td>
                    <td>${guest.numQuarto}</td>
                    <td>${guest.descricao}</td>
                    <td>
                    	<button class="btn btn-sm btn-warning" onclick="showEditGuestForm(${guest.id}, '${guest.numQuarto}', '${guest.descricao}')">Edit</button>
                    	<button class="btn btn-sm btn-danger" onclick="deleteGuest(${guest.id})">Delete</button>
                    </td>
                 </tr>`
			);
		});
	});
}

function showAddGuestForm() {
	$('#formTitle').text('Add quarto');
	$('#guestId').val('');
	$('#guestnumQuarto').val('');
	$('#guestdescricao').val('');
	$('#guestFormModal').show();
}

function showEditGuestForm(id, numQuarto, descricao) {
	$('#formTitle').text('Edit Quarto');
	$('#guestId').val(id);
	$('#guestnumQuarto').val(numQuarto);
	$('#guestdescricao').val(descricao);
	$('#guestFormModal').show();
}

function closeGuestForm() {
	$('#guestFormModal').hide();
}

function addGuest() {
	const guest = {
		numQuarto: $('#guestnumQuarto').val(),
		descricao: $('#guestdescricao').val(),
		
		
	};
	$.ajax({
		url: '/quarto/',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(guest),
		success: function() {
			closeGuestForm();
			loadGuests();
		}
	});
}

function updateGuest(id) {
	const guest = {
		numQuarto: $('#guestnumQuarto').val(),
		descricao: $('#guestdescricao').val(),
	};
	$.ajax({
		url: `/quarto/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(guest),
		success: function() {
			closeGuestForm();
			loadGuests();
		}
	});
}

function deleteGuest(id) {
	if (confirm('Realmente vai deletar?')) {
		$.ajax({
			url: `/quarto/${id}`,
			type: 'DELETE',
			success: function() {
				loadGuests();
			}
		});
	}
}
