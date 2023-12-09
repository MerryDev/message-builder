package net.quantrax.messagebuilder.backend.database;

public record Credentials(String host, String port, String database, String username, String password) {
}
