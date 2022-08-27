package com.test.swissborg.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}