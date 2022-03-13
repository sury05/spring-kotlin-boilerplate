package com.starter.kopring.exception

class UserNotFoundException: RuntimeException {
    constructor(errorMessage: String): super(errorMessage)
    constructor(errorMessage: String, throwable: Throwable): super(errorMessage, throwable)
}
