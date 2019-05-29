package dog.snow.androidrecruittest.core.rx

import io.reactivex.subjects.BehaviorSubject

class Variable<T>(private val defaultValue: T) {
    var value: T = defaultValue
        set(value) {
            field = value
            observable.onNext(value)
        }
    val observable = BehaviorSubject.createDefault(value)
}