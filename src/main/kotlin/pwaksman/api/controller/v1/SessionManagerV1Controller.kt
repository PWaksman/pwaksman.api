package pwaksman.api.controller.v1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pwaksman.api.adapter.SessionManagerTransportAdapter
import pwaksman.api.service.SessionManagerService
import pwaksman.api.transport.v1.SessionManagerV1Transport

@RestController
@RequestMapping("/api/v1/SessionManager")
class SessionManagerV1Controller {

    @Autowired
    private lateinit var service: SessionManagerService

    @Autowired
    private lateinit var adapter: SessionManagerTransportAdapter

    @PostMapping
    fun save(@RequestBody data: SessionManagerV1Transport): SessionManagerV1Transport {
        val result = service.save(adapter.toCurrent(data))

        return adapter.toV1(result)
    }

    @GetMapping("{id}")
    fun get(@PathVariable("id") id: Long): SessionManagerV1Transport {
        val result = service.get(id)

        return adapter.toV1(result)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") id: Long): String {
        service.delete(id)
        return "deleted"
    }
}