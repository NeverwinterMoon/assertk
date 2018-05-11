package assertk

expect class AssertionFailedError(
    message: String?,
    expected: Any?,
    actual: Any?,
    cause: Throwable?
) : AssertionError

expect open class MultipleFailuresError(heading: String, failures: List<Throwable>) : AssertionError