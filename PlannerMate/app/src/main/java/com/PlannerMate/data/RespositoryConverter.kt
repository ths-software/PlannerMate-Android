package com.PlannerMate.data

import com.PlannerMate.data.services.CitaApi
import com.PlannerMate.data.services.ClienteApi
import com.PlannerMate.data.services.TrabajadorApi
import com.PlannerMate.models.Cita
import com.PlannerMate.models.Cliente
import com.PlannerMate.models.Trabajador

fun Cita.toCitaApi() = CitaApi(
    this.idCita,
    this.nombre,
    this.contacto,
    this.fecha,
    this.descripcion,
    this.trabajador,
    this.cliente
)

fun CitaApi.toCita() = Cita(
    this.idCita,
    this.nombre,
    this.contacto,
    this.fecha,
    this.descripcion,
    this.trabajador,
    this.cliente
)


fun Cliente.toClienteApi() = ClienteApi(
    this.idCliente,
    this.nombre,
    this.contacto,
    this.password
)

fun ClienteApi.toCliente() = Cliente(
    this.idCliente,
    this.nombre,
    this.contacto,
    this.password
)

fun Trabajador.toTrabajadorApi() = TrabajadorApi(
    this.idTrabajador,
    this.nombre,
    this.contacto
)

fun TrabajadorApi.toTrabajador() = Trabajador(
    this.idTrabajador,
    this.nombre,
    this.contacto
)