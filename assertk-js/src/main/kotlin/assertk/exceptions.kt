package assertk

actual class AssertionFailedError actual constructor(
    message: String?,
    expected: Any?,
    actual: Any?,
    override val cause: Throwable?
) : AssertionError(message)

actual open class MultipleFailuresError actual constructor(heading: String, private val failures: List<Throwable>) :
    AssertionError() {

    private val heading = if (heading.isBlank()) "Multiple Failures" else heading.trim()

    override val message: String?
        get() {
            val failureCount = failures.size
            if (failureCount == 0) {
                return heading
            }
            val builder = StringBuilder(this.heading)
                .append(" (")
                .append(failureCount).append(" ")
                .append(pluralize(failureCount, "failure", "failures"))
                .append(")")
                .append("\n")

            val lastIndex = failureCount - 1
            for (failure in failures.subList(0, lastIndex)) {
                builder.append("\t").append(nullSafeMessage(failure)).append("\n")
            }
            builder.append("\t").append(nullSafeMessage(this.failures[lastIndex]))

            return builder.toString()
        }

    private fun pluralize(count: Int, singular: String, plural: String): String {
        return if (count == 1) singular else plural
    }

    private fun nullSafeMessage(failure: Throwable): String {
        return failure.message.let { message ->
            if (message.isNullOrBlank()) {
                "<no message> in " + failure::class.simpleName
            } else {
                message!!
            }
        }
    }
}

