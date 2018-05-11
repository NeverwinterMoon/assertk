package assertk.internal

expect class AssertionFailedError(
    message: String?,
    expected: Any? = null,
    actual: Any? = null,
    cause: Throwable? = null
) : AssertionError

expect open class MultipleFailuresError(heading: String, failures: List<Throwable>) : AssertionError