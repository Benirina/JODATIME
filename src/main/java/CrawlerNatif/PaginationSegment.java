package CrawlerNatif;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.*;

public class PaginationSegment
{
    public static void main( String[] args )
    {
        final StringBuilder sb1 = new StringBuilder();
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;

        try
        {
            // Parameter pagination counts
            int startCount = 0;
            int limitCount = 841;

            // Loop 32 times, 158 schools / 5 (pagination amount)
            for( int i = 0; i < 841; i++ )
            {
                // Open a connection to the supplied URL
                final URLConnection urlConnection = new URL( "https://www.developpez.com/" ).openConnection();
                // Tell the URL we are sending output
                urlConnection.setDoOutput( true );
                // The stream we will be writing to the URL
                outputStream = urlConnection.getOutputStream();

                // Setup parameters for pagination
                final String params = "qstart=" + startCount + "&limit=" + limitCount;
                // Get the bytes of the pagination parameters
                final byte[] outputInBytes = params.getBytes( "UTF-8" );
                // Write the bytes to the URL
                outputStream.write( outputInBytes );

                // Get and read the URL response
                bufferedReader = new BufferedReader( new InputStreamReader( urlConnection.getInputStream() ) );
                StringBuilder response = new StringBuilder();
                String inputLine;

                // Loop over the response and read each line appending it to the StringBuilder
                while( (inputLine = bufferedReader.readLine()) != null )
                {
                    response.append( inputLine );
                }

                // Do the same as before just with a String instead
                final Document doc = Jsoup.parse( response.toString() );
                Elements links = doc.select( "div.pagination" );
                links.forEach( e -> sb1.append( e.text() ).append( System.getProperty( "line.separator" ) ) );

                // Increment the pagination parameters
                startCount += 4;
                limitCount += 4;
            }

            System.out.println( sb1.toString() );
            //jTextArea1.setText(sb1.toString());
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // Close the bufferedReader
                if( bufferedReader != null )
                {
                    bufferedReader.close();
                }

                // Close the outputStream
                if( outputStream != null )
                {
                    outputStream.close();
                }
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
        }
    }
}
