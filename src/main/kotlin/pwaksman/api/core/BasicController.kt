package pwaksman.api.core

interface BasicController<Transport, Id, Service: BasicService<Transport, Id>>