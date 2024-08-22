
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
	$.getJSON('/hospede/', function(data) {
		$('#guestTableBody').empty();
		data.forEach(guest => {
			$('#guestTableBody').append(
				`<tr>
					<td>${guest.id}</td>
                    <td>${guest.name}</td>
                    <td>${guest.cpf}</td>
                    <td>${guest.email}</td>
                    <td>
                    	<button class="btn btn-sm bg-secondary" onclick="showEditGuestForm(${guest.id}, '${guest.name}', '${guest.cpf}', '${guest.email}')">Edit</button>
                    	<button class="btn btn-sm btn-danger" onclick="deleteGuest(${guest.id})">Delete</button>
                    </td>
                 </tr>`
			);
		});
	});
}

function showAddGuestForm() {
	$('#formTitle').text('Add hospede');
	$('#guestId').val('');
	$('#guestName').val('');
	$('#guestCpf').val('');
	$('#guestEmail').val('');
	$('#guestFormModal').show();
}

function showEditGuestForm(id, name, cpf, email) {
	$('#formTitle').text('Edit Hospede');
	$('#guestId').val(id);
	$('#guestName').val(name);
	$('#guestCpf').val(cpf);
	$('#guestEmail').val(email);
	$('#guestFormModal').show();
}

function closeGuestForm() {
	$('#guestFormModal').hide();
}

function addGuest() {
	const guest = {
		name: $('#guestName').val(),
		cpf: $('#guestCpf').val(),
		email: $('#guestEmail').val(),
		
	};
	$.ajax({
		url: '/hospede/',
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
		name: $('#guestName').val(),
		cpf: $('#guestCpf').val(),
		email: $('#guestEmail').val()
	};
	$.ajax({
		url: `/hospede/${id}`,
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
			url: `/hospede/${id}`,
			type: 'DELETE',
			success: function() {
				loadGuests();
			}
		});
	}
}
