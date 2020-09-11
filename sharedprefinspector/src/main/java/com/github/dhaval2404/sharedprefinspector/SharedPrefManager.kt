package com.github.dhaval2404.sharedprefinspector

import android.content.Context
import android.util.Log
import com.github.dhaval2404.sharedprefinspector.constant.Action
import com.github.dhaval2404.sharedprefinspector.data.SharedPrefFactory
import com.github.dhaval2404.sharedprefinspector.data.entity.SharedPref
import java.util.Collections

/**
 * SharedPreferences DisplayUtil class
 *
 * @author Dhaval Patel
 * @version 1.0
 * @since 09 Sep 2020
 */
class SharedPrefManager(context: Context, private val prefName: String) {

    private val mSharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private val mSharedPrefRepository = SharedPrefFactory.getSharedPrefRepository(context)

    /**
     * Checks whether the preferences contains a preference.
     *
     * @param key The name of the preference to check.
     * @return Returns true if the preference exists in the preferences,
     *         otherwise false.
     */
    fun contains(key: String): Boolean {
        return mSharedPreferences.contains(key)
    }

    fun getString(key: String): String? {
        return get(key, null)
    }

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a String.
     *
     **/
    fun get(key: String, defValue: String?): String? {
        return mSharedPreferences.getString(key, defValue)
    }

    fun getStringSet(key: String): MutableSet<String>? {
        return get(key, Collections.emptySet())
    }

    /**
     * Retrieve a set of String values from the preferences.
     *
     * <p>Note that you <em>must not</em> modify the set instance returned
     * by this call.  The consistency of the stored data is not guaranteed
     * if you do, nor is your ability to modify the instance at all.
     *
     * @param key The name of the preference to retrieve.
     * @param defValues CalendarEntity to return if this preference does not exist.
     *
     * @return Returns the preference values if they exist, or defValues.
     * Throws ClassCastException if there is a preference with this name
     * that is not a Set.
     *
     */
    fun get(key: String, defValues: MutableSet<String>): MutableSet<String> {
        val set = mSharedPreferences.getStringSet(key, defValues)
        if (set != null) return set
        return Collections.emptySet()
    }

    fun getInt(key: String): Int {
        return get(key, 0)
    }

    /**
     * Retrieve an int value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     *
     */
    fun get(key: String, defValue: Int): Int {
        return mSharedPreferences.getInt(key, defValue)
    }

    fun getLong(key: String): Long {
        return get(key, 0L)
    }

    /**
     * Retrieve a long value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a long.
     *
     */
    fun get(key: String, defValue: Long): Long {
        return mSharedPreferences.getLong(key, defValue)
    }

    fun getFloat(key: String): Float {
        return get(key, 0.0f)
    }


    /**
     * Retrieve a float value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a float.
     *
     */
    fun get(key: String, defValue: Float): Float {
        return mSharedPreferences.getFloat(key, defValue)
    }

    fun getBoolean(key: String): Boolean {
        return get(key, false)
    }

    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a boolean.
     *
     */
    fun get(key: String, defValue: Boolean): Boolean {
        return mSharedPreferences.getBoolean(key, defValue)
    }

    /**
     * Set a String value in the preferences editor, to be written back once
     * commit is called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.  Passing {@code null}
     *    for this argument is equivalent to calling {@link #remove(String)} with
     *    this key.
     */
    fun put(key: String, value: String?) {
        if (value != null) {
            putObject(key, value)
        } else {
            remove(key)
        }
    }

    /**
     * Set a set of String values in the preferences editor, to be written
     * commit is called.
     *
     * @param key The name of the preference to modify.
     * @param values The set of new values for the preference.  Passing {@code null}
     *    for this argument is equivalent to calling {@link #remove(String)} with
     *    this key.
     */
    fun put(key: String, values: Set<String>?) {
        putObject(key, values)
    }

    /**
     * Set an int value in the preferences editor, to be written back once
     * commit is called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     *
     */
    fun put(key: String, value: Int) {
        putObject(key, value)
    }

    /**
     * Set a long value in the preferences editor, to be written back once
     * commit is called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     *
     */
    fun put(key: String, value: Long) {
        putObject(key, value)
    }

    /**
     * Set a float value in the preferences editor, to be written back once
     * commit is called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     *
     */
    fun put(key: String, value: Float) {
        putObject(key, value)
    }

    /**
     * Set a boolean value in the preferences editor, to be written back
     * commit is called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     *
     */
    fun put(key: String, value: Boolean) {
        putObject(key, value)
    }

    /**
     * Mark in the editor to remove <em>all</em> values from the
     * preferences.  Once commit is called, the only remaining preferences
     * will be any that you have defined in this editor.
     *
     * <p>Note that when committing back to the preferences, the clear
     * is done first, regardless of whether you called clear before
     * or after put methods on this editor.
     *
     * @return Returns true if the SharedPreferences clear successfully
     */
    fun clear() {
        mSharedPreferences.edit().clear().apply()

        val sharedPref = SharedPref(
            action = Action.CLEAR,
            key = prefName
        )
        mSharedPrefRepository.insert(sharedPref)
    }

    /**
     * Mark in the editor that a preference value should be removed, which
     * will be done in the actual preferences once commit is called.
     *
     * <p>Note that when committing back to the preferences, all removals
     * are done first, regardless of whether you called remove before
     * or after put methods on this editor.
     *
     * @param key The name of the preference to remove.
     *
     * @return Returns true if the SharedPreferences remove key successfully
     */
    fun remove(key: String) {
        Log.e("SharePref", "Remove $key")
        mSharedPreferences.edit().remove(key).apply()
        val sharedPref = SharedPref(
            action = Action.DELETE,
            key = key
        )
        mSharedPrefRepository.insert(sharedPref)
    }

    /**
     * Set a Any value in the preferences editor, to be written back once
     * commit is called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.  Passing {@code null}
     *    for this argument is equivalent to calling {@link #remove(String)} with
     *    this key.
     */
    private fun putObject(key: String, value: Any?) {
        val isUpdate = contains(key)

        val editor = mSharedPreferences.edit()
        when (value) {
            is Boolean -> editor.putBoolean(key, value)
            is Int -> editor.putInt(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            is String -> editor.putString(key, value)
            is Enum<*> -> editor.putString(key, value.toString())
            null -> editor.remove(key)
            else -> {
                if (value is List<*> || value is Set<*>) {
                    editor.putStringSet(key, (value as Collection<*>).map { it.toString() }.toSet())
                } else {
                    throw RuntimeException("Attempting to save non-supported preference")
                }
            }
        }
        editor.apply()

       val sharedPref = SharedPref(
           action = if (isUpdate) Action.UPDATE else Action.ADD,
            key = key,
            value = value.toString()
        )
        mSharedPrefRepository.insert(sharedPref)
    }

}
