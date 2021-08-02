package pwaksman.api.core

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.jpa.repository.JpaRepository

abstract class BaseService<Transport, Model, Id> : BasicService<Transport, Id> {

    protected abstract val repository: JpaRepository<Model, Id>
    protected abstract fun Model.toTransport(): Transport
    protected abstract fun Transport.toModel(): Model
    protected open fun Transport.isValid() {}

    override fun save(data: Transport): Transport {
        data.isValid()

        val model = data.toModel()
        val savedModel = repository.save(model)

        return savedModel.toTransport()
    }

    override fun get(id: Id): Transport {
        val model = repository.findById(id)

        if (model.isPresent)
            return model.get().toTransport()

        throw DataNotFound()
    }

    override fun delete(id: Id) {
        try{
            repository.deleteById(id)
        }catch (ex: EmptyResultDataAccessException){
            throw DataNotFound()
        }
    }
}