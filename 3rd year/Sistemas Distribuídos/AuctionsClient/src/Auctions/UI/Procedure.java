/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auctions.UI;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Andre
 */
@FunctionalInterface
public interface Procedure extends MenuFunctions
{
    public void invoke();
}
