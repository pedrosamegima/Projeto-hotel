
$(document).ready(function() {
	loadReserva();

	$('#reservaForm').submit(function(event) {
		event.preventDefault();
		const id = $('#reservaId').val();
		if (id) {
			updateReserva(id);
		} else {
			addReserva();
		}
	});
});

function loadReserva() {
	$.getJSON('/reserva/', function(data) {
		$('#reservaTableBody').empty();
		data.forEach(reserva => {
			$('#reservaTableBody').append(
				`<tr>
					<td>${reserva.id}</td>
                    <td>${reserva.hospede.name}</td>
                    <td>${reserva.quarto.numQuarto}</td>
                    <td>${reserva.checkIn}</td>
                    <td>${reserva.checkOut}</td>
                    <td>
                    	<button class="btn btn-sm btn-warning" onclick="showEditReservaForm(${reserva.id}, '${reserva.hospede.id}', '${reserva.quarto.id}', '${reserva.checkIn}', '${reserva.checkOut}')">Edit</button>
                    	<button class="btn btn-sm btn-danger" onclick="deleteReserva(${reserva.id})">Delete</button>
                    </td>
                 </tr>`
			);
		});
	});
}

function showAddReservaForm() {
	$('#formTitle').text('Add Reserva');
	$('#reservaId').val('');
	$('#reservaName').val('');
	$('#reservaNumQuarto').val('');
	$('#reservaCheckIn').val('');
	$('#reservaCheckOut').val('');
	$('#reservaFormModal').show();
}

function showEditReservaForm(id, name, numQuarto, checkIn, checkOut) {
	$('#formTitle').text('Edit Reserva');
	$('#reservaId').val(id);
	$('#reservaName').val(name);
	$('#reservaNumQuarto').val(numQuarto);
	$('#reservaCheckIn').val(checkIn);
	$('#reservaCheckOut').val(checkOut);
	$('#reservaFormModal').show();
}

function closeReservaForm() {
	$('#reservaFormModal').hide();
}

function addReserva() {
	const reserva = {
		hospede: { id: $('#reservaName').val() },
		quarto: { id: $('#reservaNumQuarto').val() },
		checkIn: $('#reservaCheckIn').val(),
		checkOut: $('#reservaCheckOut').val(),



	};
	$.ajax({
		url: '/reserva/',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(reserva),
		success: function() {
			closeReservaForm();
			loadReserva();
		}
	});
}

function updateReserva(id) {
	const reserva = {
		hospede: { id: $('#reservaName').val() },
		quarto: { id: $('#reservaNumQuarto').val() },
		checkIn: $('#reservaCheckIn').val(),
		checkOut: $('#reservaCheckOut').val(),
	};
	$.ajax({
		url: `/reserva/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(reserva),
		success: function() {
			closeReservaForm();
			loadReserva();
		}
	});
}

function deleteReserva(id) {
	if (confirm('Realmente vai deletar?')) {
		$.ajax({
			url: `/reserva/${id}`,
			type: 'DELETE',
			success: function() {
				loadReserva();
			}

		});
	}
}
