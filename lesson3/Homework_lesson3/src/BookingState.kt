sealed class BookingState {
    object Pending : BookingState()
    object Confirmed : BookingState()
    object Cancelled : BookingState()
}
