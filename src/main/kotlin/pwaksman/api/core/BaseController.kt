package pwaksman.api.core

import org.springframework.web.bind.annotation.*

abstract class BaseController<Transport, Id, Service : BasicService<Transport, Id>> : BasicController<Transport, Id, Service> {

    protected abstract var service: Service

    @PostMapping
    fun save(@RequestBody data: Transport): Transport {
        return service.save(data)
    }

    @GetMapping("{id}")
    fun get(@PathVariable("id") id: Id): Transport {
        return service.get(id)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") id: Id): String {
        service.delete(id)
        return "deleted"
    }
}