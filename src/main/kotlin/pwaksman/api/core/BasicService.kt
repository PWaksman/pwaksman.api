package pwaksman.api.core

interface BasicService<Transport, Id> {
    fun save(data: Transport): Transport
    fun get(id: Id): Transport
    fun delete(id: Id)
}