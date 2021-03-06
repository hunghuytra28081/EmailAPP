package bravo.mail.emailapp.utils

import com.intuit.sdp.BuildConfig

object Constant {
    const val KEY_ITERATIONS = 65536
    const val KEY_LENGTH = 256

    const val REQUEST_SOUND_INBOUND = 1
    const val REQUEST_SOUND_OUTBOUND = 2
    const val REQUEST_EXPORT = 3
    const val REQUEST_IMPORT = 4
    const val REQUEST_CHOOSE_ACCOUNT = 5
    const val REQUEST_DONE = 6
    const val REQUEST_IMPORT_CERTIFICATE = 7
    const val REQUEST_OAUTH = 8
    const val REQUEST_STILL = 9
    const val REQUEST_DELETE_ACCOUNT = 10
    const val REQUEST_IMPORT_PROVIDERS = 11

    const val PI_MISC = 1

    const val ACTION_QUICK_GMAIL = BuildConfig.APPLICATION_ID + ".ACTION_QUICK_GMAIL"
    const val ACTION_QUICK_OAUTH = BuildConfig.APPLICATION_ID + ".ACTION_QUICK_OAUTH"
    const val ACTION_QUICK_SETUP = BuildConfig.APPLICATION_ID + ".ACTION_QUICK_SETUP"
    const val ACTION_QUICK_POP3 = BuildConfig.APPLICATION_ID + ".ACTION_QUICK_POP3"
    const val ACTION_VIEW_ACCOUNTS = BuildConfig.APPLICATION_ID + ".ACTION_VIEW_ACCOUNTS"
    const val ACTION_VIEW_IDENTITIES = BuildConfig.APPLICATION_ID + ".ACTION_VIEW_IDENTITIES"
    const val ACTION_EDIT_ACCOUNT = BuildConfig.APPLICATION_ID + ".EDIT_ACCOUNT"
    const val ACTION_EDIT_IDENTITY = BuildConfig.APPLICATION_ID + ".EDIT_IDENTITY"
    const val ACTION_MANAGE_LOCAL_CONTACTS = BuildConfig.APPLICATION_ID + ".MANAGE_LOCAL_CONTACTS"
    const val ACTION_MANAGE_CERTIFICATES = BuildConfig.APPLICATION_ID + ".MANAGE_CERTIFICATES"
    const val ACTION_IMPORT_CERTIFICATE = BuildConfig.APPLICATION_ID + ".IMPORT_CERTIFICATE"
    const val ACTION_SETUP_MORE = BuildConfig.APPLICATION_ID + ".SETUP_MORE"

    const val PRE_ROOM = "preference_room"
}