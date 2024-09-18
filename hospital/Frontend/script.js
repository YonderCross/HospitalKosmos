document.addEventListener('DOMContentLoaded', () => {
    cargarDoctores();
    cargarConsultorios();
});

const apiUrl = 'http://localhost:8080/api';

// Cargar doctores y consultorios para los select
async function cargarDoctores() {
    const response = await fetch(`${apiUrl}/doctores`);
    const doctores = await response.json();
    const select = document.getElementById('doctor');
    const consultaSelect = document.getElementById('consultaDoctor');

    doctores.forEach(doc => {
        const option = document.createElement('option');
        option.value = doc.idDoctor;
        option.textContent = `${doc.nombre} ${doc.apellidoPaterno}`;
        select.appendChild(option);
        consultaSelect.appendChild(option.cloneNode(true));
    });
}

async function cargarConsultorios() {
    const response = await fetch(`${apiUrl}/consultorios`);
    const consultorios = await response.json();
    const select = document.getElementById('consultorio');
    const consultaSelect = document.getElementById('consultaConsultorio');

    consultorios.forEach(con => {
        const option = document.createElement('option');
        option.value = con.idConsultorio;
        option.textContent = `Consultorio ${con.numeroConsultorio}`;
        select.appendChild(option);
        consultaSelect.appendChild(option.cloneNode(true));
    });
}

// Agregar Cita
async function agregarCita() {
    const doctor = document.getElementById('doctor').value;
    const consultorio = document.getElementById('consultorio').value;
    const horario = document.getElementById('horario').value;
    const paciente = document.getElementById('paciente').value;

    const cita = {
        idDoctor: parseInt(doctor),
        idConsultorio: parseInt(consultorio),
        horarioConsulta: horario,
        nombrePaciente: paciente
    };

    const response = await fetch(`${apiUrl}/citas`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cita)
    });

    if (response.ok) {
        alert('Cita agregada exitosamente');
    } else {
        alert('Error al agregar la cita');
    }
}

// Consultar Citas
async function consultarCitas() {
    const fecha = document.getElementById('consultaFecha').value;
    const consultorio = document.getElementById('consultaConsultorio').value;
    const doctor = document.getElementById('consultaDoctor').value;

    let url = `${apiUrl}/citas?fecha=${fecha}`;
    if (consultorio) url += `&consultorio=${consultorio}`;
    if (doctor) url += `&doctor=${doctor}`;

    const response = await fetch(url);
    const citas = await response.json();
    const resultadoDiv = document.getElementById('resultadoConsulta');

    if (citas.length > 0) {
        resultadoDiv.innerHTML = `<ul>${citas.map(cita => `<li>ID: ${cita.idCita}, Doctor: ${cita.doctoresModel.nombre} ${cita.doctoresModel.apellidoPaterno}, Consultorio: ${cita.consultoriosModel.numeroConsultorio}, Horario: ${cita.horarioConsulta}, Paciente: ${cita.nombrePaciente}</li>`).join('')}</ul>`;
    } else {
        resultadoDiv.textContent = 'No se encontraron citas.';
    }
}

// Cancelar Cita
async function cancelarCita() {
    const idCita = document.getElementById('idCitaCancelar').value;

    const response = await fetch(`${apiUrl}/citas/${idCita}`, {
        method: 'DELETE'
    });

    if (response.ok) {
        alert('Cita cancelada exitosamente');
    } else {
        alert('Error al cancelar la cita');
    }
}

// Editar Cita
async function editarCita() {
    const idCita = document.getElementById('idCitaEditar').value;
    const nuevoHorario = document.getElementById('nuevoHorario').value;

    const cita = {
        horarioConsulta: nuevoHorario
    };

    const response = await fetch(`${apiUrl}/citas/${idCita}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cita)
    });

    if (response.ok) {
        alert('Cita editada exitosamente');
    } else {
        alert('Error al editar la cita');
    }
}
