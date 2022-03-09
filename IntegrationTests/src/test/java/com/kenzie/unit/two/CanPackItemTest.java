package com.kenzie.unit.two;

import com.amazonaws.services.lambda.model.InvokeResult;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CanPackItemTest {

    @Test
    void canPackItemTest_TASK_1() {
        String departmentName = "warehouse-test";
        String userName = "warehouse-user-one";
        String packItemsRole = "pack-items";

        InvokeResult invokeResult = LambdaUtility.invokeFunction("createdepartment","{\"departmentName\": \"" + departmentName + "\"}");
        System.out.println(LambdaUtility.resultToString(invokeResult));
        assertEquals(200, (int) invokeResult.getStatusCode(), "Can Create Department warehouse");
        invokeResult = LambdaUtility.invokeFunction("createrole","{\"roleName\": \"" + packItemsRole + "\"}");
        System.out.println(LambdaUtility.resultToString(invokeResult));
        assertEquals(200, invokeResult.getStatusCode(), "Can Create Role pack-items");
        invokeResult = LambdaUtility.invokeFunction("createuser","{\"userName\": \"" + userName + "\", \"departmentName\": \"" + departmentName + "\"}");
        System.out.println(LambdaUtility.resultToString(invokeResult));https://signin.aws.amazon.com/federation?Action=login&SigninToken=YXytjrlfCW3_efS1eFgEG1PVXMsgpCKF9LK3Cyn_q6mvWpcU44A6esViatl6cK2M4at-4YYu9sel7UmFeOIC09uUPSgB0nLLfhjcmpxQcxAPfcDS-Va19m2wmREVKrzkWpOh3OvE48UPtaiZlw4UqB-YX1sDGX1DtH90NXQ2KPUPKu9RMT9jIz4-kicm8vYk4GgXvKCLOOTuOauVRK4ArJOq4BllKTc-oi7cWy1wcC2lQz4r6r7oUgl2n9bHZfooW7ctiWOemI3Q5TTIDpg3BEwJnjcz_NkK2dg-zX-45A3yvEQ0NImirVoHSX_VY2XkXK8ACyTAOcWrbR6xih-Q9GCcWrhb-tT6iSLWKTxPI2FLWzf9A9Hdb8_CF3QhtVtPiw9XnqEp0oRnScgjvGKuBJkn8gJUvUbH7YWN37kTknb7i0DQqMOEJHdT7zMUt4ZSsBYrmVlOdaTu2m2WgGSq0XlhAVE31ntxCXHljsSGpAhcfy3liZIcgoHGcGUXVYG_OIqDnCoznlawm41SPTmWDAC3lPcJJAuAEKRqihndvLwpHaXggU-YTWuFgYFOv6MnUX8fb9jBnL7KwuJDVGjOloAXFs1LIZ_6sdwsX01oe7hxKM6cPsqbzIe4BgpUzPmeV-ZzkFFMGrS536LRk25SrY9TpaxXNSjx_lu-afNY0hN15YKRkcNTYL2MXMDMaE2b74rbRu2nN-NMl-BXPO0ZfhHJflGVenR6Fdk_Q1FZrJA5yzDNf2AEnyv_rYd9fBJW-IqQ7WkV2uFNKzVDCEvuxJY279jO7R3IeXKByyjTfAhJtIwIB9tUZBkNSvjU1NjaoWm91njF_rMB8NXoqwKtbYDCeOaJhYeuq4_OekBC1iCQK7X_7n9O0a2p83T5UhTiO3rBj4gUHohdMUCGsfA9WeVJb6NpvxTeG5XMmfBKIP3ueXpHHjR18ua_6cnWG-z_sZauStxd2EMrbxCVNR0jMgYSfjq0Fatzdqfpj2vNQU_FYFWapMZq6q0J9yCYqDge38T9dUSX0T7e10EzkL21HYRFL8n1c1159FiwWmY2b4V3_2QE1StOtZrirR0z8RYMwUiywhlDCthJjiRdwOXbVpZtIEoScVwECDfBj_Nf__yVNmdhLTmxd-o2uh9TuYLzLwMR5XVUA92Qm98V9iZ3j9w_y3-LjQsGzkEiciHEpU0-auFvxXV3XR0UJ1zmV65v7lRxbGSXYCBsCvZZ7vVpUiv_NSvg1nXsV5ITNL4u2Kv9GewQOC8TeEgdBsGdwiOZIzVY3tRj1b2v8LsHR--avGHpvIksxHLU2F8GZ1MLBf8bQv_uLbKd3Et5RrqiVzBX266Lk0ONvRtqWD95UEY6x_tm_FvG4hDSfjHN82KV5y2NT6d8cvOdtVx3CLtJ_h_pQou_A2hjpkqhMgExQWuVCL0b-_ejbi5KP5RGeh3SLadZuNwuEHnl962kPw&Issuer=https%3A%2F%2Flabs.vocareum.com&Destination=https%3A%2F%2Fconsole.aws.amazon.com%2Fconsole%2Fhome%3Fregion%3Dus-east-1
        assertEquals(200, invokeResult.getStatusCode(), "Can Create user warehouse-user-one in warehouse");
        invokeResult = LambdaUtility.invokeFunction("assignusertorole","{\"userName\": \"" + userName + "\", \"roleName\": \"" + packItemsRole + "\"}");
        System.out.println(LambdaUtility.resultToString(invokeResult));
        assertEquals(200, invokeResult.getStatusCode(), "Can assign pack-items role to warehouse-user-one");

        invokeResult = LambdaUtility.invokeFunction("canwarehouseuserpackitem", "{\"userName\": \"" + userName + "\"}");
        System.out.println(LambdaUtility.resultToString(invokeResult));
        String ans = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);

        boolean result = Boolean.parseBoolean(ans);

        assertTrue(result, "warehouse-user-one has permission to pack items");
    }

}
